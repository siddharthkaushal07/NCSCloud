import { EventEmitter, Injectable } from '@angular/core';
import { ServiceLocatorService } from './service-locator.service';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor() {
  }

  /*
  * Raised when an application arror is occured
  */
  public appErrorEvent = new EventEmitter();

  /**
   * Update user menu
   */
  public changeMenu = new EventEmitter();
 
  public sessionExpired = new EventEmitter();

  appErrorOccured(text: string) {
    this.appErrorEvent.emit(text);
  }
  
  /**
   * Raise change enu event 
   * 
   * @param text 
   */
  menuChanged(text: string) {
    this.changeMenu.emit(text);
  }

  /**
   * Session expired 
   * 
   * @param text 
   */
  sessionEnd(text: string) {
    this.sessionExpired.emit(text);
  }

}