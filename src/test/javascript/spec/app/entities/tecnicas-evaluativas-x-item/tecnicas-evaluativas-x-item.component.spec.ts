import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SusaludTestModule } from '../../../test.module';
import { TecnicasEvaluativasXItemComponent } from 'app/entities/tecnicas-evaluativas-x-item/tecnicas-evaluativas-x-item.component';
import { TecnicasEvaluativasXItemService } from 'app/entities/tecnicas-evaluativas-x-item/tecnicas-evaluativas-x-item.service';
import { TecnicasEvaluativasXItem } from 'app/shared/model/tecnicas-evaluativas-x-item.model';

describe('Component Tests', () => {
  describe('TecnicasEvaluativasXItem Management Component', () => {
    let comp: TecnicasEvaluativasXItemComponent;
    let fixture: ComponentFixture<TecnicasEvaluativasXItemComponent>;
    let service: TecnicasEvaluativasXItemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [TecnicasEvaluativasXItemComponent],
      })
        .overrideTemplate(TecnicasEvaluativasXItemComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TecnicasEvaluativasXItemComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TecnicasEvaluativasXItemService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TecnicasEvaluativasXItem(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tecnicasEvaluativasXItems && comp.tecnicasEvaluativasXItems[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
