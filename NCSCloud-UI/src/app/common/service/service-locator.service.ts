import { Injectable } from '@angular/core';
import { HttpServiceService } from './http-service.service';
import { DataValidatorService } from './data-validator.service';
import { Router, ActivatedRoute } from '@angular/router';
import { EndpointServiceService } from './endpoint-service.service';
import { CookieService } from 'ngx-cookie-service';


@Injectable({
  providedIn: 'root'
})
export class ServiceLocatorService {

  http = null;
  dataValidator = null;
  router = null;
  endpoints = null;
  cookie = null;

  /**
   * User context of the application 
   */
  userContext = null;

  constructor(private hs: HttpServiceService,
    private dv: DataValidatorService,
    private r: Router, 
    private ep: EndpointServiceService,
    private cs: CookieService) {
    this.http = hs;
    this.dataValidator = dv;
    this.router = r;
    this.endpoints = ep;
    this.cookie =cs;
  }

  /**
   * get path variable from url
   * 
   * @param route 
   * @param callback 
   */
  getPathVariable(route: ActivatedRoute, callback) {
    route.params.subscribe(params => {
      callback(params)
    });
  }

  /**
   * Forward to page 
   * 
   * @param page 
   */
  forward(page) {
    this.router.navigateByUrl(page);
  }

}