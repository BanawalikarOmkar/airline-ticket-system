import { Http, Headers } from '@angular/http';
import { Injectable } from '@angular/core';
import { tokenNotExpired, JwtHelper } from 'angular2-jwt'; 
import 'rxjs/add/operator/map'; 
import { Router } from '@angular/router';


@Injectable()
export class AuthService {
  currentUser: any; 

  constructor(private http: Http, private router: Router) {
    let token = localStorage.getItem('token');
    if (token) {
      let jwt = new JwtHelper();
      this.currentUser = jwt.decodeToken(token);
    }
  }

  login(credentials) { 

    var headers = new Headers();
    headers.append("Content-Type", "application/json");

    return this.http.
     post('http://localhost:8080/spring-app/authenticate', JSON.stringify(credentials) ,{headers:headers}).
     map(response => {
      console.log(response);
      let result = response.json();
      
      if (result && result.token) {
        localStorage.setItem('token', result.token);
        let jwt = new JwtHelper();
        this.currentUser = jwt.decodeToken(localStorage.getItem('token'));
        return true; 
      }
      else return false; 
    });
  }

  logout() { 
    localStorage.removeItem('token');
    this.currentUser = null;
    this.router.navigate(['/login']);
  }

  isLoggedIn() { 
    return tokenNotExpired('token');
  }

  isAdmin(){
    let roles = this.currentUser.ROLE;
    for(let roleindex in roles){
      if(roles[roleindex].authority ==="ROLE_ADMIN"){
        return true;
      }
    }
    return false;
  }
}

