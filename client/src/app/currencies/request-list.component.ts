import {Component, OnInit, signal, WritableSignal} from '@angular/core';
import {CurrenciesHttpService} from "./currencies-http.service";
import {RequestListDatasource} from "./request-list.datasource";
import {CurrenciesRequestDto} from "./currencies-dtos";
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow, MatHeaderRowDef, MatNoDataRow, MatRow, MatRowDef,
  MatTable
} from "@angular/material/table";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-request-list',
  standalone: true,
  imports: [
    MatTable,
    MatColumnDef,
    MatHeaderCell,
    MatHeaderCellDef,
    MatCell,
    MatCellDef,
    MatHeaderRow,
    MatHeaderRowDef,
    MatRow,
    MatRowDef,
    MatNoDataRow,
    DatePipe
  ],
  templateUrl: './request-list.component.html'
})
export class RequestListComponent implements OnInit {

  private readonly requestsData: WritableSignal<CurrenciesRequestDto[]> = signal([]);
  protected readonly dataSource: RequestListDatasource = new RequestListDatasource(this.requestsData);
  protected readonly columnsDef = [
    {
      column: 'name',
      label: 'Name'
    },
    {
      column: 'currency',
      label: 'Currency'
    },
    {
      column: 'date',
      label: 'Request date'
    },
    {
      column: 'value',
      label: 'Exchange rate'
    }
  ]
  protected readonly rowsDef = this.columnsDef.map(item => item.column);

  constructor(
    private httpService: CurrenciesHttpService
  ) {
  }

  ngOnInit(): void {
    this.httpService.getCurrenciesRequests().subscribe(response =>
      this.requestsData.set(response.requests)
    );
  }
}
