import { Component, OnInit, Input } from '@angular/core';
import airportLocationInterface from '../../interface/airportLocation';

import {Observable} from 'rxjs';
import {FormControl} from '@angular/forms';
import {map, startWith} from 'rxjs/operators';
import { pipe } from 'rxjs'; 



@Component({
  selector: 'airport-selector',
  templateUrl: './airport-selector.component.html',
  styleUrls: ['./airport-selector.component.less']
})
export class AirportSelectorComponent implements OnInit {

	@Input('placeHolderText') placeHolderText : string
	@Input('locations') locations: airportLocationInterface[];
  @Input('locationCtrl') locationCtrl : FormControl;
	inputLocations: Observable<airportLocationInterface[]>;

  
  constructor() { 
       
  }

  private _filterStates(value: string): airportLocationInterface[] {
    const filterValue = value.toLowerCase();
    return this.locations.filter(location => location.name.toLowerCase().indexOf(filterValue) !== -1);
  }

  ngOnInit() {
  	this.inputLocations = this.locationCtrl.valueChanges
      .pipe(
        startWith(''),
        map(state => state ? this._filterStates(state) : this.locations.slice())
      );  	
  }

  displayName(location:airportLocationInterface) {
  	return location ? location.name : location;
  }

}


