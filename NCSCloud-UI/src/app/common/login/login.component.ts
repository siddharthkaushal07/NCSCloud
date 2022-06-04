import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BaseCtl } from '../base.component';
import { GlobalConstants } from '../GlobalConstants ';
import { EventService } from '../service/event.service';
import { ServiceLocatorService } from '../service/service-locator.service';
import { GoogleLoginProvider, AuthService } from 'angular-6-social-login';  
import { SocialLoginModule, AuthServiceConfig } from 'angular-6-social-login';  
import { UtilserviceService } from 'src/app/utilservice.service';
import { Socialusers } from './Socialusers';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent extends BaseCtl {
  response;  
    socialusers=new Socialusers();  
  public param = "";

  constructor(public locator: ServiceLocatorService,
    private eService: EventService, public route: ActivatedRoute,
    /////
    private router: Router ,
    public OAuth: AuthService,  
    private SocialloginService: UtilserviceService, 
    ) {
    super(locator.endpoints.AUTH, locator, route);
    let _self = this;
    //this.form.preload["applist"] = [];
    locator.getPathVariable(route, function (params) {
      _self.param = params["param"];
      if ('logout' === _self.param) {
        _self.logout();
      } else if ( "session" === _self.param) {
        localStorage.clear();
        _self.form.error = true;
        _self.form.message = 'OOPS your session is expired';
      } else if (_self.param != null && _self.param.length > 10) {
        _self.keyLogin(_self.param);
      }
    });

  }

  ngOnInit() {
    let _self = this;
    let uc = localStorage.getItem('userContext');
    if (uc != null) {
      _self.locator.router.navigateByUrl('/dashboard');
    }
  }
/////////////////

public socialSignIn(socialProvider: string) {  
  let socialPlatformProvider;  
   if (socialProvider === 'google') {
     console.log("google login---------------")  
    socialPlatformProvider = GoogleLoginProvider.PROVIDER_ID;  
  }  

  this.OAuth.signIn(socialPlatformProvider).then(socialusers => {  
    console.log(socialProvider , socialusers);  
    console.log("this is social user -------------------------data===="+socialusers.name);  
    this.Savesresponse(socialusers);  

  });  
}  
Savesresponse(socialusers: Socialusers) {  
  console.log("inside save response ===---------------"+socialusers.name)  
  console.log("call save response from service===---------------") 
  localStorage.setItem('socialusers', JSON.stringify( socialusers));   
  this.SocialloginService.Savesresponse(socialusers).subscribe((res: any) => {  
    
    console.log(res);  
    this.socialusers=res;  
    this.response = res.userDetail;  
    localStorage.setItem('socialusers', JSON.stringify( this.socialusers));  
    console.log("from local storage-----"+localStorage.setItem('socialusers', JSON.stringify(this.socialusers)));  
    this.router.navigate([`/dashboard`]);  
  })  
}  


  ///////////////////
  populateForm(form, data) {
    super.populateForm(form, data);
    form.data.loginId = data.loginId;
    form.data.password = data.password;
  }

  /**
   * Sign in a user
   */
  signIn() {

    var _self = this;

    console.log('signIn', this.form);

    let url =  this.locator.endpoints.AUTH + "/sso/login";
    
    //let url = this.locator.endpoints.getEndpoint(this.locator.endpoints.AUTH, "sso/login");

    this.locator.http.post(url, this.form.data, function (res, error) {
      if (error) {
        alert("Server Error:" + error);
        return;
      }
      //console.log(res);
      _self.form.message = '';
      _self.form.inputerror = {};

      //console.log("1");
      _self.form.error = !res.success;
      if (_self.locator.dataValidator.isNotNullObject(res.result.message)) {
        _self.form.message = res.result.message;
      }

      //console.log("2");
      if (_self.locator.dataValidator.isNotNullObject(res.result.inputerror)) {
        _self.form.inputerror = res.result.inputerror;
      }

      if (_self.locator.dataValidator.isTrue(res.success)) {
        //console.log("4");
        // console.log("tttttttttttttttttttt-------"+localStorage.getItem(GlobalConstants.getKey('firstName')));
        localStorage.setItem(GlobalConstants.getKey('firstName'), res.result.data.firstName);
        console.log("tttttttttttttttttttt-------"+localStorage.getItem('firstName'));
        localStorage.setItem(GlobalConstants.getKey('loginId'), res.result.data.loginId);
        localStorage.setItem( GlobalConstants.getKey('sessionId'), res.result.jsessionid);
        localStorage.setItem(GlobalConstants.getKey('skey'), res.result.data.skey);
        localStorage.setItem(GlobalConstants.getKey('appId'), res.result.data.appId);
        localStorage.setItem(GlobalConstants.getKey('roleId'), res.result.data.defaultRoleId);
        let str = JSON.stringify(res.result.data);
        localStorage.setItem(GlobalConstants.getKey('userContext'), str);
        _self.locator.userContext = JSON.parse(str);
        _self.eService.menuChanged("");
        _self.locator.router.navigateByUrl('/dashboard');
      }
    });
  }

  /**
   * 
   * @param skey Login by Auth Key
   */
  keyLogin(skey) {
    var _self = this;
    let url = _self.api.endpoint + "/sso/login/" + skey;
    this.locator.http.get(url, function (res, error) {
      if (error) {
        return;
      }

      //console.log(res);
      _self.form.message = '';
      _self.form.inputerror = {};

      //console.log("1");
      _self.form.error = !res.success;
      if (_self.locator.dataValidator.isNotNullObject(res.result.message)) {
        _self.form.message = res.result.message;
      }

      //console.log("2");
      if (_self.locator.dataValidator.isNotNullObject(res.result.inputerror)) {
        _self.form.inputerror = res.result.inputerror;
      }

      if (_self.locator.dataValidator.isTrue(res.success)) {
        //console.log("4");
        localStorage.setItem(GlobalConstants.getKey('loginId'), res.result.data.loginId);
        localStorage.setItem(GlobalConstants.getKey('sessionId'), res.result.jsessionid);
        localStorage.setItem(GlobalConstants.getKey('skey'), res.result.data.skey);
        localStorage.setItem(GlobalConstants.getKey('appId'), res.result.data.appId);
        localStorage.setItem(GlobalConstants.getKey('roleId'), res.result.data.defaultRoleId);
        let str = JSON.stringify(res.result.data);
        localStorage.setItem(GlobalConstants.getKey('userContext'), str);
        _self.locator.userContext = JSON.parse(str);
        _self.eService.menuChanged("");
        _self.locator.router.navigateByUrl('/dashboard');
      }


    });
  }
  /**
   * Logout a user
   */
  logout() {
    let _self = this;
    let skey =  localStorage.getItem(GlobalConstants.getKey('skey'));
    let url = this.locator.endpoints.getEndpoint(this.locator.endpoints.AUTH, "sso/logout/" + skey);
    this.locator.http.get(url, function (res, error) {
      if (error) {
        return;
      }
      localStorage.clear();
      _self.locator.userContext = {};
      _self.eService.menuChanged("");
      _self.locator.router.navigateByUrl('/login');
    });
  }


  /**
   * Forgot password
   */
   forgotPassword(){
    let _self = this;

    _self.form.message = '';


    let login =  _self.form.data["loginId"];

    if(login == null || login.length == 0 ){
      _self.form.error = true;
      _self.form.message = "Invalid Login ID";
      return;
    }else{
      _self.form.error = false;
      _self.form.message = "";
    }

    //alert(_self.form.data["loginId"]);
    let data = {
      loginId : _self.form.data["loginId"]
    }

    let url =  this.locator.endpoints.AUTH + "/sso/fp";

    this.locator.http.post(url,data, function (res, error) {
      if (error) {
        return;
      }
      _self.form.error = !res.success;
      _self.form.message = res.result.message;
    });
   }
}
