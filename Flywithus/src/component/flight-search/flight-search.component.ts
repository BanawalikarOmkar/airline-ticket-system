import { Component, OnInit } from '@angular/core';
import airportLocationInterface from '../../interface/airportLocation';
import {FormControl, FormGroup, Validators} from '@angular/forms';

import {AirportService} from './../../service/airport.service';



@Component({
  selector: 'flight-search',
  templateUrl: './flight-search.component.html',
  styleUrls: ['./flight-search.component.less']
})
export class FlightSearchComponent implements OnInit {
    location: airportLocationInterface[];
    selectedLocations : object = {};
    currentDate = new Date();
    ticket : number;
    formValid: boolean = true;
    form: FormGroup;
    
    constructor( private airportService : AirportService){

      this.form = new FormGroup({
        tripCtrl : new FormControl('2'),
        location : new FormGroup({
          fromLocationCtrl : new FormControl('', Validators.required),
          toLocationCtrl : new FormControl('', Validators.required)
        }),
        date : new FormGroup({
          startDate : new FormControl('', Validators.required),
          returnDate : new FormControl('', Validators.required)
        }),
        ticket : new FormGroup({
          adults : new FormControl('', Validators.required)
        }),
        submit : new FormControl()
      });

      airportService.getAll().subscribe((response)=> {
        this.location = response;
      });


      this.getFormControl('tripCtrl').valueChanges.subscribe(value=>{
        if(value == 2){
          this.getFormControl('date','returnDate').setValidators(Validators.required); ;
        }else{
          this.getFormControl('date','returnDate').clearValidators()
        }

      });
    }

    checkReturnAndStartDate(){
      return this.getFormControl('tripCtrl').value == '2' && 
              this.getFormControl('date','returnDate').touched && 
              this.getFormControl('date','startDate').touched &&
              (this.getFormControl('date','returnDate').value < 
                this.getFormControl('date','startDate').value);
    }

    ngOnInit() {
      this.formValid = false;
      var temp = this.form.get('submit');
    }

    getFormControl(parent, child?){
      if(parent && child)
        return this.form.get(parent).get(child);
      if(parent)
        return this.form.get(parent)
    }

    search(){
      if(!this.form.invalid){
        let temp:airportLocationInterface[]  = 
              this.location.filter(airport => (
                airport.airportId == this.getFormControl('location','fromLocationCtrl').value.airportId ||
                                      airport.airportId == this.getFormControl('location','toLocationCtrl').value.airportId ));
        for (var i in temp) {
          this.selectedLocations[temp[i].airportId ] = temp[i];
        }
        this.formValid = true;
        console.log(this.form.value);
      }
    }

}
