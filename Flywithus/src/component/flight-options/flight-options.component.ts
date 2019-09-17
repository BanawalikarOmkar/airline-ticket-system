import { Component, OnInit, Input, ViewChild, Output, EventEmitter } from '@angular/core';
import {flightOptionsInteface,RefundType} from '../../interface/flightOptions';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import {SelectionModel} from '@angular/cdk/collections';


@Component({
  selector: 'app-flight-options',
  templateUrl: './flight-options.component.html',
  styleUrls: ['./flight-options.component.less']
})
export class FlightOptionsComponent implements OnInit {

    @Input("flightArray") flightArray: flightOptionsInteface[];
	  @Output() flightSelected = new EventEmitter<flightOptionsInteface>();  
	  @ViewChild(MatSort, {}) sort: MatSort;

  	flightOptions :flightOptionsInteface[]
  	dataSource : MatTableDataSource<flightOptionsInteface>
  	displayedColumns: string[] = ['select', 'airlineName', 'startTime', 'endTime', 'totalPrice'];
  	selection = new SelectionModel<flightOptionsInteface>(false, []);
  
  	constructor() { }

    ngOnInit() {
        this.flightOptions = this.flightArray;
        this.dataSource = new MatTableDataSource(this.flightOptions);
  	    this.dataSource.sort = this.sort;
    }

    onSelect(row){
    	this.flightSelected.emit(row);
    }

    /** Whether the number of selected elements matches the total number of rows. */
    isAllSelected() {
      const numSelected = this.selection.selected.length;
      const numRows = this.dataSource.data.length;
      return numSelected === numRows;
    }

    /** Selects all rows if they are not all selected; otherwise clear selection. */
    masterToggle() {
      this.isAllSelected() ?
          this.selection.clear() :
          this.dataSource.data.forEach(row => this.selection.select(row));
    }

    /** The label for the checkbox on the passed row */
    checkboxLabel(row?: flightOptionsInteface): string {
      if (!row) {
        return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
      }
      return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.id + 1}`;
    }

    applyFilter(filterValue: string) {
      this.dataSource.filter = filterValue.trim().toLowerCase();
    }

}
