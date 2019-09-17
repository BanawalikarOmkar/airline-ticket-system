
import {DataService} from './data.service';
import 'rxjs/add/operator/catch';
import { Injectable } from '@angular/core';
import {AuthHttp} from 'angular2-jwt';

@Injectable()
export class FlightService extends DataService{

	//2019-08-03/3/13/12

	 url : string;
	 http : AuthHttp
	constructor(http : AuthHttp){
		var temp = "http://localhost:8080/spring-app/home/flight/";
		super(temp, http);
		this.url = temp;
		this.http = http;
		
	}

	getMatchedFlights(startDate : string, tickets : number, tolocation: number, fromLocation: number){
	  	return this.http.get(this.url+`${startDate}/${tickets}/${tolocation}/${fromLocation}`).map(response => response.json()).catch(this.handleError);

	}


}