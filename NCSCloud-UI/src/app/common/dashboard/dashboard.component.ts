import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SocialLoginModule, AuthServiceConfig, AuthService,SocialUser } from 'angular-6-social-login';  
import { Socialusers } from '../login/Socialusers';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  socialusers = new Socialusers();  
  constructor(public OAuth: AuthService,    private router: Router) { }

  ngOnInit() {
    this.socialusers = JSON.parse(localStorage.getItem('socialusers'));  
    console.log("username=============== "+this.socialusers.name);  
  }

  logout() {  
    
     this.OAuth.signOut().then(data => {  
       localStorage.removeItem("socialusers")
       this.router.navigate([`/login`]);  
     });  
   }  
}
