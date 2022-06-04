import { Component } from '@angular/core';
import { EventService } from './common/service/event.service';
import { ServiceLocatorService } from './common/service/service-locator.service';
import { Subscription } from 'rxjs';
import { GlobalConstants } from './common/GlobalConstants ';
import { ActivatedRoute, NavigationStart } from '@angular/router';
import { BaseCtl } from './common/base.component';
import { AuthService,GoogleLoginProvider, SocialUser } from 'angular-6-social-login';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent extends BaseCtl {

  title = 'NCSCloud-UI';

  /**
   * Menu items 
   */
  public menuItems = [];
  public userContext = {};
  //public form = {};
  public ctxPath = "";

  //Change menu event
  private eventChangeMenu: Subscription;

  private eventSessionExpired: Subscription;

  private previousUrl = "";

  //Refresh broswer 
  private eventRefresh: Subscription;

  constructor(public locator: ServiceLocatorService, public eService: EventService,public route: ActivatedRoute) {
    super(locator.endpoints.AUTH, locator, route);
  }


  //Subscribe to error event
  ngOnInit() {
    let _self = this;
    _self.form["roleId"] = 0;
    this.ctxPath = GlobalConstants.CONTEXT_PATH;

    //event when menu changed
    this.eventChangeMenu = this.eService.changeMenu.subscribe(
      text => {
        console.log("inside app component ")
        _self.userContext = _self.locator.userContext;
        _self.menuItems = [];
        _self.getMenu();
        console.log('App Comp', _self.userContext);
      }
    );

    //session expired event
    this.eventSessionExpired = this.eService.sessionExpired.subscribe(
      text => {
        this.locator.forward("login/session");
      }
    );    

    //Broswer refesh event
    this.eventRefresh = this.locator.router.events.subscribe( (event) => {
      if (event instanceof NavigationStart) {
        if(this.menuItems.length ==0){
          //this.eService.menuChanged("");
        }
        this.previousUrl = this.locator.router.url;      
      }
    });     
  }

  getMenu() {
    let _self = this;
    let appId = localStorage.getItem(GlobalConstants.getKey('appId'));
    let roleId = localStorage.getItem(GlobalConstants.getKey('roleId'));
    this.form["roleId"] =  _self.locator.dataValidator.toInt(roleId);

    _self.menuItems = [];

    console.log("app component : ", appId, roleId);

    if (!appId || !roleId) {
      return;
    }

    //let url = _self.locator.endpoints.MENU_ASSIGNMENT + "/json/" + appId + "/" + roleId;
    let url = this.locator.endpoints.getEndpoint(this.locator.endpoints.AUTH, "sso/menu");
    url += "/" + + appId + "/" + roleId;

    this.locator.http.get(url, function (res, error) {
      if (error) {
        return;
      }
      _self.menuItems = res;
      _self.locator.router.navigateByUrl('/dashboard');
      console.log("res--",res);
    });
  }

  /**
   * Chaneg User role
   */
  changeRole() {
    localStorage.setItem(GlobalConstants.getKey("roleId"),this.form["roleId"] + "");
    this.eService.menuChanged("");
    for ( let i =0; i < this.userContext["roles"].length; i++){
      if(this.userContext["roles"][i].id == this.form["roleId"]){
        this.userContext["roleName"] = this.userContext["roles"][i].value;
      }
    }
  }

  /**
   * Click on Home button
   */
  gotoHome(){
    if(this.userContext["name"]){
      this.locator.router.navigateByUrl('/dashboard');
    }else{
      this.locator.router.navigateByUrl('/login');
    }
  }
}
