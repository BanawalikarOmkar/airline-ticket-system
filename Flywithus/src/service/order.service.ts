import {DataService} from './data.service';
import 'rxjs/add/operator/catch';
import { Injectable } from '@angular/core';
import {AuthHttp} from 'angular2-jwt';

@Injectable()
export class OrderService extends DataService{

	url : string;
	http : AuthHttp
	constructor(http : AuthHttp){
		var temp = "http://localhost:8080/spring-app/home/order";
		super(temp, http);
		this.url = temp;
		this.http = http;
	}


	getAllbyUserName(username){
		return this.http.get(this.url+`/${username}`).catch(this.handleError).map(response => response.json());
	}
}