import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '../../service/auth.service';


@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.less']
})
export class LoginComponent implements OnInit {

	invalidLogin : boolean;
  credentials : string;
  	constructor(
  	private router: Router,
  	private authService: AuthService) { }

  ngOnInit() {
  }

  signIn(credentials){
    this.credentials = credentials.username;
  	this.authService.login(credentials)
  		.subscribe(result => {
			if (result){
				console.log("User valid");
        this.router.navigate(['/book']);
			}else  
	       this.invalidLogin = true; 
  		},
  		error =>{
        alert('Login credentials wrong')
  			console.log(error);
  		});
  }

}
