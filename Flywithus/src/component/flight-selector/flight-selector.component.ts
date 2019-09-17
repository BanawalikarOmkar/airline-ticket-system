import { Component, OnInit, OnDestroy, Input, Inject } from '@angular/core';
import {flightOptionsInteface, flightOptionsInteface2} from '../../interface/flightOptions';
import { Order} from '../../interface/order';
import {FlightService} from '../../service/flight.service';
import {OrderService} from '../../service/order.service';
import {AuthService} from '../../service/auth.service';
import { formatDate } from '@angular/common';
import airportLocationInterface from '../../interface/airportLocation';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-flight-selector',
  templateUrl: './flight-selector.component.html',
  styleUrls: ['./flight-selector.component.less']
})
export class FlightSelectorComponent implements OnInit, OnDestroy {

  @Input("airports") airports : object;
  @Input("searchCriteria") searchCriteria :  flightOptionsInteface2;
	oneWayFlights : flightOptionsInteface[]
	returnFlights : flightOptionsInteface[]
  oneWayFlightSelected : flightOptionsInteface
  returnFlightSelected : flightOptionsInteface
  showTotalPrice : boolean = false;
  currentAirports : [];
  totalPrice : number;
  orderSucessful : Order[] = [];


  constructor(private flightService : FlightService, 
    private orderService : OrderService,
    private authService : AuthService,
    public dialog: MatDialog) {}

  ngOnInit() {

    this.flightService.getMatchedFlights(
      formatDate(
        this.searchCriteria.date.startDate,"yyyy-MM-dd","en-US"),
        this.searchCriteria.ticket.adults,
        this.searchCriteria.location.toLocationCtrl.airportId,
        this.searchCriteria.location.fromLocationCtrl.airportId,
      ).subscribe((response: flightOptionsInteface[]) =>{
        this.oneWayFlights = response;

    });
    if(this.searchCriteria.tripCtrl == "2"){
      this.flightService.getMatchedFlights(
        formatDate(
          this.searchCriteria.date.returnDate,"yyyy-MM-dd","en-US"),
          this.searchCriteria.ticket.adults,
          this.searchCriteria.location.fromLocationCtrl.airportId,
          this.searchCriteria.location.toLocationCtrl.airportId,
      ).subscribe((response: flightOptionsInteface[]) =>{
        this.returnFlights = response;
    });
    }
    console.log("app-flight-selector");
    console.log(this.searchCriteria);
  }


  onOneWayFlightSelected(flight: flightOptionsInteface){
  	this.oneWayFlightSelected = flight;
    this.oneWayFlightSelected.fromAirportName = 
      this.airports[this.oneWayFlightSelected.fromLocation].name;
    this.oneWayFlightSelected.toAirportName = 
      this.airports[this.oneWayFlightSelected.toLocation].name;

    this.setShowTotalPrice();
  	console.log(flight);
  }

  onReturnWayFlightSelected(flight: flightOptionsInteface){
  	this.returnFlightSelected = flight;
    this.returnFlightSelected.fromAirportName = 
      this.airports[this.returnFlightSelected.fromLocation].name;
    this.returnFlightSelected.toAirportName = 
      this.airports[this.returnFlightSelected.toLocation].name;
    this.setShowTotalPrice();
  	console.log(flight)
  }

  setShowTotalPrice(){                
    if(typeof this.oneWayFlightSelected !== 'undefined' && typeof this.returnFlightSelected !== 'undefined') {
       this.showTotalPrice = true;
       this.totalPrice =  this.oneWayFlightSelected.totalPrice  + this.returnFlightSelected.totalPrice ;
    }
    if(typeof this.oneWayFlightSelected !== 'undefined' && typeof this.returnFlightSelected === 'undefined'){
       this.showTotalPrice = true;
       this.totalPrice =  this.oneWayFlightSelected.totalPrice;
    }
    if(typeof this.oneWayFlightSelected === 'undefined' && typeof this.returnFlightSelected !== 'undefined'){
       this.showTotalPrice = false;
    }
    if(typeof this.oneWayFlightSelected === 'undefined' && typeof this.returnFlightSelected === 'undefined'){
       this.showTotalPrice = false;
    }
                                 
  }

  order(searchButton){
    searchButton.disabled = true;
    let temp = this.oneWayFlightSelected;
    let order = new Order(this.authService.currentUser.sub,new Date(), this.searchCriteria.ticket.adults, this.oneWayFlightSelected.flightId);
    this.orderService.create(order).subscribe((response: Order) =>{
      response['location'] = {from:this.oneWayFlightSelected.fromAirportName,to:this.oneWayFlightSelected.toAirportName};
      this.orderSucessful.push(response);
      this.openDialog();

    });

    if(this.returnFlightSelected){
      let order = new Order(this.authService.currentUser.sub,new Date(), this.searchCriteria.ticket.adults, this.returnFlightSelected.flightId);
      this.orderService.create(order).subscribe(response => {
          response['location'] = {from:this.returnFlightSelected.fromAirportName,to:this.returnFlightSelected.toAirportName};

          this.orderSucessful.push(response);
      });

    }
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(OrderOverviewExampleDialog, {
      width: '650px',
      data: this.orderSucessful
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  ngOnDestroy(){
    // this.flightService.unsubscribe();
  }
}

@Component({
  selector: 'order-overview-dialog',
  templateUrl: 'order-overview-dialog.html',
})
export class OrderOverviewExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<OrderOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Order) {}

  

}