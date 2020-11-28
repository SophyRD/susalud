import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { FuenteReferenciaXItemDetailComponent } from 'app/entities/fuente-referencia-x-item/fuente-referencia-x-item-detail.component';
import { FuenteReferenciaXItem } from 'app/shared/model/fuente-referencia-x-item.model';

describe('Component Tests', () => {
  describe('FuenteReferenciaXItem Management Detail Component', () => {
    let comp: FuenteReferenciaXItemDetailComponent;
    let fixture: ComponentFixture<FuenteReferenciaXItemDetailComponent>;
    const route = ({ data: of({ fuenteReferenciaXItem: new FuenteReferenciaXItem(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [FuenteReferenciaXItemDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FuenteReferenciaXItemDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FuenteReferenciaXItemDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load fuenteReferenciaXItem on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fuenteReferenciaXItem).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
