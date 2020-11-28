import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFuenteReferencial } from 'app/shared/model/fuente-referencial.model';

@Component({
  selector: 'jhi-fuente-referencial-detail',
  templateUrl: './fuente-referencial-detail.component.html',
})
export class FuenteReferencialDetailComponent implements OnInit {
  fuenteReferencial: IFuenteReferencial | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fuenteReferencial }) => (this.fuenteReferencial = fuenteReferencial));
  }

  previousState(): void {
    window.history.back();
  }
}
