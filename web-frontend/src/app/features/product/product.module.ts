import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { ProductListComponent } from './product-list/product-list.component';
import { NgModule } from "@angular/core";
import {MatGridListModule} from '@angular/material/grid-list';
import {MatCardModule} from '@angular/material/card';

@NgModule({
  declarations:[
    ProductListComponent
  ],
  imports: [
    CommonModule,
    MatGridListModule,
    MatCardModule,
    MatButtonModule
  ],
  exports: [
    ProductListComponent
  ]
})

export class ProductModule {

}
