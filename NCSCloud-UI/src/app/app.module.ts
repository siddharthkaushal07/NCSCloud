import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';


//Common Component Start
import { SignupComponent } from './common/signup/signup.component';
import { LoginComponent } from './common/login/login.component';
import { AppInfoComponent } from './common/app-info/app-info.component';
import { AppConfigComponent } from './common/app-config/app-config.component';
import { ApplicationInfoComponent } from './common/application-info/application-info.component';
import { ErrorLogComponent } from './common/error-log/error-log.component';
import { MyProfileComponent } from './common/my-profile/my-profile.component';
import { DashboardComponent } from './common/dashboard/dashboard.component';
import { ErrorComponent } from './common/apperror.component';
import { FormErrorComponent } from './common/form-error';
import { FormInputComponent } from './common/form-input';
import { ListHeaderComponent } from './common/list-header/list-header.component';
import { ListBodyComponent } from './common/list-body/list-body.component';

import { HttpServiceService } from './common/service/http-service.service';
import { ServiceLocatorService } from './common/service/service-locator.service';
import { EndpointServiceService } from './common/service/endpoint-service.service';
import { DataValidatorService } from './common/service/data-validator.service';
import { EventService } from './common/service/event.service';
//Common Component End

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { ProductComponent } from './product/product.component';

import { OfferComponent } from './offer/offer.component';
import { OrderComponent } from './order/order.component';
import { TaxComponent } from './tax/tax.component';
import { ShoppingComponent } from './shopping/shopping.component';
import { DomainComponent } from './domain/domain.component';
import { SubcategoryComponent } from './subcategory/subcategory.component';
import { CategoryComponent } from './category/category.component';
import { EnquiryComponent } from './enquiry/enquiry.component';
import { SupplierComponent } from './supplier/supplier.component';
import { ComplainComponent } from './complain/complain.component';
import { ProductpriceComponent } from './productprice/productprice.component';
import { ProductmodelComponent } from './productmodel/productmodel.component';
import { ProductratingComponent } from './productrating/productrating.component';
import { CatalogueComponent } from './catalogue/catalogue.component';
import { ShippingComponent } from './shipping/shipping.component';
import {ProductviewComponent} from './productview/productview.component';
import { BillingComponent } from './billing/billing.component';

import { PaymentComponent } from './payment/payment.component';
import { UtilserviceService } from './utilservice.service';
import { ReviewOrderComponent } from './review-order/review-order.component';
import { OrderSuccessComponent } from './order-success/order-success.component';
import { WishListComponent } from './wish-list/wish-list.component';
import { SellerRegisrationComponent } from './seller-regisration/seller-regisration.component';
import { BussinessSettingComponent } from './bussiness-setting/bussiness-setting.component';
import { SellerLoginComponent } from './seller-login/seller-login.component';
import { DatePipe } from '@angular/common';
import { OrderItemComponent } from './order-item/order-item.component';
import { DateFormaterPipe } from './date-formater.pipe';
import { GoogleLoginProvider, AuthService } from 'angular-6-social-login';  
import { SocialLoginModule, AuthServiceConfig } from 'angular-6-social-login';

const routes: Routes = [
];



export function socialConfigs() { 
  console.log("inside social configuration in app module ts-----------") 
  const config = new AuthServiceConfig(  
    [  
     
      {  
        id: GoogleLoginProvider.PROVIDER_ID,  
        provider: new GoogleLoginProvider('667203440057-gsoaa2b7p77n7ujksih76pm5n32urf9s.apps.googleusercontent.com')  
      }  
    ]  );  
    return config;  
  }  

@NgModule({
  declarations: [
    AppComponent,
    //Common Compnent Start
    SignupComponent,
    LoginComponent,
    AppInfoComponent,
    AppConfigComponent,
    ApplicationInfoComponent,
    ErrorLogComponent,
    MyProfileComponent,
    DashboardComponent,
    ErrorComponent,
    FormErrorComponent,
    FormInputComponent,
    ListHeaderComponent,
    ListBodyComponent,
    ProductComponent,
    ShoppingComponent,
    OfferComponent,
    OrderComponent,
    TaxComponent,
    DomainComponent,
    SubcategoryComponent,
    CategoryComponent,
    EnquiryComponent,
    SupplierComponent,
    ComplainComponent,
    ProductpriceComponent,
    ProductmodelComponent,
    ProductratingComponent,
    CatalogueComponent,
    ShippingComponent,
    ProductviewComponent,
    BillingComponent,
    PaymentComponent,
    ReviewOrderComponent,
    OrderSuccessComponent,
    WishListComponent,
    
    SellerRegisrationComponent,
    BussinessSettingComponent,
    SellerLoginComponent,
    OrderItemComponent,
    DateFormaterPipe,
 
    
    //Common Compnent Start

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,

    RouterModule.forRoot(routes, { useHash: true })    
  ],
  providers: [
    DatePipe,
    CookieService,
    HttpServiceService,
    ServiceLocatorService,
    EndpointServiceService,
    DataValidatorService,
    EventService,
      UtilserviceService,
    
      AuthService,  
      {  
        provide: AuthServiceConfig,  
        useFactory: socialConfigs  
      }  
    ],  
  bootstrap: [AppComponent]
})
export class AppModule { 
  
}
