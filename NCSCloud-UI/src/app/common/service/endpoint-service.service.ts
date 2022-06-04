import { Injectable } from '@angular/core';
import { GlobalConstants } from '../GlobalConstants ';

@Injectable({
  providedIn: 'root'
})

export class EndpointServiceService {

  public SERVER_URL =  GlobalConstants.SERVER_URL;
  public DOC_API = GlobalConstants.DOC_API;

  constructor() {
  }

  public AUTH = GlobalConstants.SERVER_URL + "/auth";
  public DOC = GlobalConstants.SERVER_URL + "/Doc";

  //Common
  public DATABASE = GlobalConstants.SERVER_URL + "/database";
  public APPINFO = GlobalConstants.SERVER_URL + "/appinfo";
  public APPCONFIG = GlobalConstants.SERVER_URL + "/appconfig";
  public ERROR_LOG = GlobalConstants.SERVER_URL + "/errorlog";
  public PRODUCT = GlobalConstants.SERVER_URL + "/Product";
  public SHOPPING = GlobalConstants.SERVER_URL + "/Shopping";
  public TAX = GlobalConstants.SERVER_URL + "/Tax";
  public ORDER = GlobalConstants.SERVER_URL + "/Order";
  public OFFER = GlobalConstants.SERVER_URL + "/Offer";
  public SUBCATEGORY = GlobalConstants.SERVER_URL + "/SubCategory";
  public CATEGORY = GlobalConstants.SERVER_URL + "/Category";
  public DOMAIN = GlobalConstants.SERVER_URL + "/Domain";
  public ENQUIRY = GlobalConstants.SERVER_URL + "/Enquiry";
  public SUPPLIER = GlobalConstants.SERVER_URL + "/Supplier";
  public COMPLAIN = GlobalConstants.SERVER_URL + "/Complain";
  public PRODUCTPRICE = GlobalConstants.SERVER_URL + "/ProductPrice";
  public PRODUCTMODEL = GlobalConstants.SERVER_URL + "/ProductModel";
  public PRODUCTRATING = GlobalConstants.SERVER_URL + "/ProductRating";
  public CATALOGUE = GlobalConstants.SERVER_URL + "/Product";
  public SHIPPING = GlobalConstants.SERVER_URL + "/Shipping";
  public BILLING = GlobalConstants.SERVER_URL + "/Billing";
  public WISHLIST = GlobalConstants.SERVER_URL + "/WishList";
  public SELLERREGISTRATION = GlobalConstants.SERVER_URL + "/SellerRegistration";
  public GSTREGISTRATION = GlobalConstants.SERVER_URL + "/GSTRegistration";
  public ORDERITEM = GlobalConstants.SERVER_URL + "/OrderItem";



  
  public getEndpoint(url: string, suburl: string) {
    return url + "/" + suburl;
  }

}
