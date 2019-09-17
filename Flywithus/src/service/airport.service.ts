
import {DataService} from './data.service';
import {AuthHttp} from 'angular2-jwt';
import 'rxjs/add/operator/catch';
import { Injectable } from '@angular/core';


@Injectable()
export class AirportService extends DataService{

	//2019-08-03/3/13/12

	 url : string;
	 http : AuthHttp
	constructor(http : AuthHttp){
		var temp = "http://localhost:8080/spring-app/home/airport";
		super(temp, http);
		this.url = temp;
		this.http = http;
		
	}


}