import {Component, Inject} from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatDivider} from "@angular/material/divider";
import {MatButton} from "@angular/material/button";
import {ErrorResponseDto} from "../interceptor/error-response-dto";

@Component({
  selector: 'app-http-error',
  standalone: true,
  imports: [
    MatDialogTitle,
    MatDialogContent,
    MatDivider,
    MatButton,
    MatDialogActions,
    MatDialogClose
  ],
  templateUrl: './http-error.component.html',
  styleUrl: './http-error.component.css'
})
export class HttpErrorComponent {

  public serverError: boolean = false;
  public clientError: boolean = false;
  public unknownError: boolean = false;

  constructor(@Inject(MAT_DIALOG_DATA) protected data: ErrorResponseDto) {
  }
}
