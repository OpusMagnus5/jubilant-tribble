import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {MatDialog} from "@angular/material/dialog";
import {HttpErrorComponent} from "../component/http-error.component";
import {ErrorResponseDto} from "./error-response-dto";

@Injectable({providedIn: "root"})
export class ServerErrorHttpInterceptor implements HttpInterceptor {

  constructor(private dialog: MatDialog) {
  }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req).pipe(
          catchError(error => {
            if (error instanceof HttpErrorResponse){
              const response = (<HttpErrorResponse> error).error as ErrorResponseDto
              const statusCode: number = error.status
              const dialogRef = this.dialog.open(HttpErrorComponent,
                {
                  data: response,
                  disableClose: true,
                  maxWidth: '50em'
                });
              if (statusCode.toString().startsWith('5')) {
                dialogRef.componentInstance.serverError = true;
              } else if (statusCode.toString().startsWith('4')) {
                dialogRef.componentInstance.clientError = true;
              } else {
                dialogRef.componentInstance.unknownError = true;
              }
            }
            throw error;
          })
        )
    }
}
