import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { SusaludSharedModule } from 'app/shared/shared.module';
import { SusaludCoreModule } from 'app/core/core.module';
import { SusaludAppRoutingModule } from './app-routing.module';
import { SusaludHomeModule } from './home/home.module';
import { SusaludEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    SusaludSharedModule,
    SusaludCoreModule,
    SusaludHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    SusaludEntityModule,
    SusaludAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class SusaludAppModule {}
