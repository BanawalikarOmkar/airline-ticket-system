import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatAutocompleteModule} from '@angular/material/autocomplete';
import { ReactiveFormsModule }   from '@angular/forms';
import { FormsModule }   from '@angular/forms';
import { RouterModule } from '@angular/router'; 

import { DemoMaterialModule} from '../material-module';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FlightSearchComponent } from '../component/flight-search/flight-search.component';
import { AirportSelectorComponent } from '../component/airport-selector/airport-selector.component';
import { FlightOptionsComponent } from '../component/flight-options/flight-options.component';
import { FlightSelectorComponent, OrderOverviewExampleDialog } from '../component/flight-selector/flight-selector.component';
import { AppErrorHandler } from './common/app-error-handler';
import { LoginComponent } from '../component/login-component/login-component.component';
import { HttpModule, Http, RequestOptions} from '@angular/http';
import { AuthHttp, AUTH_PROVIDERS, provideAuth, AuthConfig } from 'angular2-jwt/angular2-jwt';


import {AuthService} from '../service/auth.service';
import {AdminAuthGuard} from '../service/admin-auth-guard.service';
import {AuthGuard} from '../service/auth-guard.service';

import {DataService} from '../service/data.service';
import {FlightService} from '../service/flight.service';
import { AirportService } from '../service/airport.service';
import {OrderService} from '../service/order.service';


import { OrderComponent } from '../component/order/order.component';
import { AdminComponent } from '../component/admin/admin.component';
import { NoAccessComponent } from '../component/no-access/no-access.component';


export function getAuthHttp(http:Http,  options: RequestOptions) {
  return new AuthHttp(new AuthConfig({
    tokenName: 'token',
    globalHeaders: [{'Content-Type':'application/json'}],
  }), http, options);
}

@NgModule({
  declarations: [
    AppComponent,
    AirportSelectorComponent,
    FlightSearchComponent,
    FlightOptionsComponent,
    FlightSelectorComponent,
    LoginComponent,
    OrderComponent,
    AdminComponent,
    NoAccessComponent,
    OrderOverviewExampleDialog
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    DemoMaterialModule,  
    HttpModule,
    RouterModule.forRoot([
      { path: '', component: LoginComponent },
      { path: 'admin', component: AdminComponent , canActivate: [AdminAuthGuard] },
      { path: 'login', component: LoginComponent },
      { path: 'order', component: OrderComponent ,  canActivate: [AuthGuard]},
      { path: 'book', component: FlightSearchComponent ,  canActivate: [AuthGuard]},
      { path: 'no-access', component: NoAccessComponent }
    ])

  ],
  providers: [
    AuthService,
    FlightService,
    AirportService,
    DataService,
    OrderService,
    AdminAuthGuard,
    AuthGuard,
    { provide : ErrorHandler, 
      useClass: AppErrorHandler
    },
    AuthHttp,
    {
      provide: AuthHttp,
      useFactory: getAuthHttp,
      deps: [Http, RequestOptions]
    },
  ],
  entryComponents : [
    OrderOverviewExampleDialog
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
