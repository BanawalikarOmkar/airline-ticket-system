import { Component, OnInit, ViewChild } from '@angular/core';
import {OrderService} from '../../service/order.service';
import {FlightService} from '../../service/flight.service';
import {AuthService} from '../../service/auth.service';
import { Order} from '../../interface/order';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import {SelectionModel} from '@angular/cdk/collections';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.less']
})
export class OrderComponent implements OnInit {

	@ViewChild(MatSort, {}) sort: MatSort;
	displayedColumns: string[] = ['orderId', 'fromAirport', 'toAirport','orderDate', 'adultTickets', 'delete'];
  	dataSource : MatTableDataSource<Order>;
  	orders : Order[]

  	constructor( private orderService : OrderService,
    	private authService : AuthService ) { 
  	}

	applyFilter(filterValue: string) {
		this.dataSource.filter = filterValue.trim().toLowerCase();
	}

	ngOnInit() {
		this.orderService.getAllbyUserName(this.authService.currentUser.sub).subscribe((response:Order[]) =>{
			this.orders = response;
			this.dataSource = new MatTableDataSource(response);
			this.dataSource.sort = this.sort;
		});
	}

	delete(button){
		this.orderService.delete(button.orderId).subscribe(reponse => {
			this.dataSource.data = this.dataSource.data.filter(order => order.flightId != button.flightId);
		});
	}

}
