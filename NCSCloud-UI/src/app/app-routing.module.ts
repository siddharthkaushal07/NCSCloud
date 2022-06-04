import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

//Common Component Start
import { SignupComponent } from './common/signup/signup.component';
import { LoginComponent } from './common/login/login.component';
import { AppInfoComponent } from './common/app-info/app-info.component';
import { AppConfigComponent } from './common/app-config/app-config.component';
import { ApplicationInfoComponent } from './common/application-info/application-info.component';
import { ErrorLogComponent } from './common/error-log/error-log.component';
import { MyProfileComponent } from './common/my-profile/my-profile.component';
import { DashboardComponent } from './common/dashboard/dashboard.component';
import { ProductComponent } from './product/product.component';
import { ShoppingComponent } from './shopping/shopping.component';
import { OfferComponent } from './offer/offer.component';
import { OrderComponent } from './order/order.component';
import { TaxComponent } from './tax/tax.component';
import { SubcategoryComponent } from './subcategory/subcategory.component';
import { CategoryComponent } from './category/category.component';
import { DomainComponent } from './domain/domain.component';
import { EnquiryComponent } from './enquiry/enquiry.component';
import { SupplierComponent } from './supplier/supplier.component';
import { ComplainComponent } from './complain/complain.component';
import { ProductpriceComponent } from './productprice/productprice.component';
import { ProductmodelComponent } from './productmodel/productmodel.component';
import { ProductratingComponent } from './productrating/productrating.component';
import { CatalogueComponent } from './catalogue/catalogue.component';
import { ShippingComponent } from './shipping/shipping.component';
import { ProductviewComponent } from './productview/productview.component';
import { BillingComponent } from './billing/billing.component';
import { PaymentComponent } from './payment/payment.component';
import { ReviewOrderComponent } from './review-order/review-order.component';
import { OrderSuccessComponent } from './order-success/order-success.component';
import { WishListComponent } from './wish-list/wish-list.component';
import { SellerRegisrationComponent } from './seller-regisration/seller-regisration.component';
import { BussinessSettingComponent } from './bussiness-setting/bussiness-setting.component';
import { SellerLoginComponent } from './seller-login/seller-login.component';
import { OrderItemComponent } from './order-item/order-item.component';




//Common Component End


const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'signup',
    component: SignupComponent
  },
  {
    path: 'billing',
    component: BillingComponent
  },
  {

    path: 'productview/:id',
    component: ProductviewComponent
  },
  {
    path: 'billingParam/:id',
    component: BillingComponent
  },
  {
    path: 'login/:param',
    component: LoginComponent
  },
  {
    path: 'info',
    component: AppInfoComponent
  },
  {
    path: 'logout',
    component: LoginComponent
  },
  {
    path: 'sessionOut',
    component: LoginComponent
  },
  {
    path: 'myprofile',
    component: MyProfileComponent
  },

  {
    path: 'product',
    component: ProductComponent
  },

  {
    path: 'shopping',
    component: ShoppingComponent
  },
  {
    path: 'category',
    component: CategoryComponent
  },

  {
    path: 'offer',
    component: OfferComponent
  },
  {
    path: 'review-order',
    component: ReviewOrderComponent
  },
  {
    path: 'success-order',
    component: OrderSuccessComponent
  },
  {
    path: 'order',
    component: OrderComponent
  
  },


  {
    path: 'tax',
    component: TaxComponent
  },

  {
    path: 'domain',
    component: DomainComponent
  },


  {
    path: 'subcategory',
    component: SubcategoryComponent
  },


  {
    path: 'enquiry',
    component: EnquiryComponent
  },

  {
    path: 'supplier',
    component: SupplierComponent
  },

  {
    path: 'complain',
    component: ComplainComponent
  },

  {
    path: 'productprice',
    component: ProductpriceComponent
    
  },

  {
    path: 'productmodel',
    component: ProductmodelComponent
    
  },

  {
    path: 'productrating',
    component: ProductratingComponent
    
  },

  {
    path: 'catalogue',
    component: CatalogueComponent
    
  },

  {
    path: 'shipping-billing',
    component: ShippingComponent
    
  },
  {
    path: 'payment',
    component: PaymentComponent
    
  },

  {
    path: 'appinfo',
    component: ApplicationInfoComponent
  }, {
    path: 'signup',
    component: SignupComponent
  }, {
    path: 'errorlog',
    component: ErrorLogComponent
  }, {
    path: 'appconfig',
    component: AppConfigComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: 'wish-List',
    component: WishListComponent
  },
  {
    path: 'seller-regisration',
    component: SellerRegisrationComponent
    
  },
  {
    path: 'order-item',
    component: OrderItemComponent
    
  },
  {
path : 'bussiness-setting',
component: BussinessSettingComponent
  },
  {
    path : 'seller-login',
    component: SellerLoginComponent
      },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
