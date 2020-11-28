import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFuenteReferenciaXItem } from 'app/shared/model/fuente-referencia-x-item.model';

@Component({
  selector: 'jhi-fuente-referencia-x-item-detail',
  templateUrl: './fuente-referencia-x-item-detail.component.html',
})
export class FuenteReferenciaXItemDetailComponent implements OnInit {
  fuenteReferenciaXItem: IFuenteReferenciaXItem | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fuenteReferenciaXItem }) => (this.fuenteReferenciaXItem = fuenteReferenciaXItem));
  }

  previousState(): void {
    window.history.back();
  }
}
