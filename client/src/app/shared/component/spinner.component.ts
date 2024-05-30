import {Component} from '@angular/core';
import {MatProgressSpinner} from "@angular/material/progress-spinner";

@Component({
  selector: 'app-spinner',
  standalone: true,
  imports: [
    MatProgressSpinner
  ],
  templateUrl: './spinner.component.html'
})
export class SpinnerComponent {

}
