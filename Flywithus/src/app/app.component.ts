import { Component } from '@angular/core';
import {AuthService} from '../service/auth.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {
  title = 'Fly with Us';

  constructor(private authService: AuthService) { }
}
