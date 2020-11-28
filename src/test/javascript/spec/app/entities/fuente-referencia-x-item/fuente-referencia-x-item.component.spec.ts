import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SusaludTestModule } from '../../../test.module';
import { FuenteReferenciaXItemComponent } from 'app/entities/fuente-referencia-x-item/fuente-referencia-x-item.component';
import { FuenteReferenciaXItemService } from 'app/entities/fuente-referencia-x-item/fuente-referencia-x-item.service';
import { FuenteReferenciaXItem } from 'app/shared/model/fuente-referencia-x-item.model';

describe('Component Tests', () => {
  describe('FuenteReferenciaXItem Management Component', () => {
    let comp: FuenteReferenciaXItemComponent;
    let fixture: ComponentFixture<FuenteReferenciaXItemComponent>;
    let service: FuenteReferenciaXItemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [FuenteReferenciaXItemComponent],
      })
        .overrideTemplate(FuenteReferenciaXItemComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FuenteReferenciaXItemComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FuenteReferenciaXItemService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FuenteReferenciaXItem(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.fuenteReferenciaXItems && comp.fuenteReferenciaXItems[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
