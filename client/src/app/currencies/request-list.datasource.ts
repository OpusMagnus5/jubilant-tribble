import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {CurrenciesRequestDto} from "./currencies-dtos";
import {WritableSignal} from "@angular/core";
import {Observable} from "rxjs";
import {toObservable} from "@angular/core/rxjs-interop";

export class RequestListDatasource implements DataSource<CurrenciesRequestDto> {

  constructor(
    private dataSource: WritableSignal<CurrenciesRequestDto[]>
  ) {
  }

  connect(collectionViewer: CollectionViewer): Observable<readonly CurrenciesRequestDto[]> {
    return toObservable(this.dataSource);
  }

  disconnect(collectionViewer: CollectionViewer): void {
  }

}
