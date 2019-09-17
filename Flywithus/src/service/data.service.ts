import { Injectable } from '@angular/core';

import {Observable} from 'rxjs';

import {BadInput} from '../app/common/bad-input';

import {NotFoundError} from '../app/common/not-found-error';

import {AppError} from '../app/common/app-error';

import {AuthHttp} from 'angular2-jwt';

import 'rxjs/add/operator/catch';

import 'rxjs/add/observable/throw';

@Injectable()
export class DataService {
  url : string
   http : AuthHttp

  constructor(  url: string,  http : AuthHttp) { 
    this.url = url;
    this.http = http;
  }


  getAll(){
  	return this.http.get(this.url).catch(this.handleError).map(response => response.json());
  }

  create(resource){
  	return this.http.post(this.url, JSON.stringify(resource)).catch(this.handleError).map(response => response.json());
  }

  update(resource){
  	return this.http.patch(this.url+'/'+resource.id,JSON.stringify(resource)).map(response => response.json());;
  }

  delete(id){
  	return this.http.delete(this.url+"/"+id).catch(this.handleError).map(response => response.json());;
  }

  handleError(error: Response){
  	if(error.status === 400)
  		return Observable.throw(new BadInput(error.json()));

  	if(error.status === 404)
  		return Observable.throw(new NotFoundError(error.json()));
  		 
  	return Observable.throw(new AppError(error));

  }
}
