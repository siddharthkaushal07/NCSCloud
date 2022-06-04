import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { EventService } from './service/event.service';

/**
 * Display application errors in yellow bar at the top of the page
 */
@Component({
  selector: 'app-error',
  template: `
    <div *ngIf="error" class="alert alert-warning">
      <strong> 
        <button (click)="reset()" class="btn btn-outline-danger btn-sm" >
          <i class="fas fa-times-circle"></i>
        </button>&nbsp;{{message}}
      </strong>
    </div>
  `
})
export class ErrorComponent implements OnInit, OnDestroy {

  //Error Message
  private message: string = "";

  //Has error 
  private error: boolean = false;

  //Event listner
  private errorSub: Subscription;

  constructor(public eService: EventService) {
  }

  //Subscribe to error event
  ngOnInit() {
    let _self = this;
    this.errorSub = this.eService.appErrorEvent.subscribe(
      text => {
        _self.message = text;
        _self.error = true;
      }
    );
  }

  //Reset message
  reset(){
    this.error =false;
    this.message = "";
  }

  //Clean event
  ngOnDestroy() {
    if (this.errorSub) {
      this.errorSub.unsubscribe();
      this.errorSub = null;
    }
  }
}