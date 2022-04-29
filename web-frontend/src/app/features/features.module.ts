import { MatButtonModule } from '@angular/material/button';
import { PaymentModule } from './payment/payment.module';
import { LayoutModule } from './layout/layout.module';
import { ProductModule } from './product/product.module';
import { NgModule } from "@angular/core";

@NgModule({
  declarations:[],
  imports: [
    ProductModule,
    LayoutModule,
    PaymentModule,
    MatButtonModule
  ],
  exports: []
})

export class FeatureModule {

}
