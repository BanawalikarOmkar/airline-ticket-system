import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {AirportService} from './../../service/airport.service';
import { FlightSearchComponent } from './flight-search.component';

describe('FlightSearchComponent', () => {
  let component: FlightSearchComponent;
  let fixture: ComponentFixture<FlightSearchComponent>;
  let service : AirportService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FlightSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    service = new AirportService(null);
    fixture = TestBed.createComponent(FlightSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    debugger;
    expect(component).toBeTruthy();
  });
});
