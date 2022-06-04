import { DOCUMENT } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ReplaySubject, Observable, forkJoin } from 'rxjs';
import { HttpServiceService } from './common/service/http-service.service';
import { ServiceLocatorService } from './common/service/service-locator.service';

@Injectable({
  providedIn: 'root'
})
export class UtilserviceService {

  private _loadedLibraries: { [url: string]: ReplaySubject<any> } = {};

  constructor(@Inject(DOCUMENT) private readonly document: any,public http: HttpClient,public locator: ServiceLocatorService,public route:Router) {}

  // forkjoin parameters will grow when we are adding any new external library into app
  lazyLoadLibrary(resourceURL): Observable<any> {
      return forkJoin([
          this.loadScript(resourceURL)
      ]);
  }

  private loadScript(url: string): Observable<any> {
      if (this._loadedLibraries[url]) {
          return this._loadedLibraries[url].asObservable();
      }
  
      this._loadedLibraries[url] = new ReplaySubject();
  
      const script = this.document.createElement('script');
      script.type = 'text/javascript';
      script.async = true;
      script.src = url;
      script.onload = () => {
          this._loadedLibraries[url].next();
          this._loadedLibraries[url].complete();
      };
  
      this.document.body.appendChild(script);    
      return this._loadedLibraries[url].asObservable();
  }   

  url;

  Savesresponse(responce)
  {
   // let url =  this.locator.endpoints.AUTH + "/sso/login";
  let url =  'http://localhost:4200/#/dashboard';
  this.route.navigateByUrl("/dashboard")  
  return this.http.post(url,responce);
    
  }
 
}