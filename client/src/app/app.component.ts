import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {map, Observable, shareReplay} from "rxjs";
import {MatIconButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {MatToolbar} from "@angular/material/toolbar";
import {MatSidenav, MatSidenavContainer, MatSidenavContent} from "@angular/material/sidenav";
import {MatListItem, MatNavList} from "@angular/material/list";
import {AsyncPipe} from "@angular/common";
import {GET_CURRENCIES_PATH, REQUESTS_PATH} from "./app.routes";
import {RouterService} from "./shared/router.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    MatIconButton,
    MatIcon,
    MatToolbar,
    MatSidenavContent,
    MatListItem,
    RouterLink,
    MatSidenav,
    MatNavList,
    MatSidenavContainer,
    AsyncPipe
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  protected isHandset$: Observable<boolean>;

  constructor(
    private breakpointObserver: BreakpointObserver,
    private routerService: RouterService
  ) {
    this.isHandset$ = this.breakpointObserver.observe(Breakpoints.XSmall)
      .pipe(
        map(result => result.matches),
        shareReplay()
      );
  }

  protected readonly GET_CURRENCIES_PATH = GET_CURRENCIES_PATH;

  protected isActivePath(path: string): boolean {
    return this.routerService.isActivePath(path);
  }

  protected readonly REQUESTS_PATH = REQUESTS_PATH;
}
