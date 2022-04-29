import { PaymentModule } from './../payment/payment.module';
import { HomePageComponent } from './home-page/home-page.component';
import { ProductModule } from './../product/product.module';
import { NgModule } from "@angular/core";

@NgModule({
  declarations:[
    HomePageComponent
  ],
  imports: [
    ProductModule,
    PaymentModule
  ],
  exports: []
})

export class LayoutModule {

}
