import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { TecnicasEvaluativasXItemUpdateComponent } from 'app/entities/tecnicas-evaluativas-x-item/tecnicas-evaluativas-x-item-update.component';
import { TecnicasEvaluativasXItemService } from 'app/entities/tecnicas-evaluativas-x-item/tecnicas-evaluativas-x-item.service';
import { TecnicasEvaluativasXItem } from 'app/shared/model/tecnicas-evaluativas-x-item.model';

describe('Component Tests', () => {
  describe('TecnicasEvaluativasXItem Management Update Component', () => {
    let comp: TecnicasEvaluativasXItemUpdateComponent;
    let fixture: ComponentFixture<TecnicasEvaluativasXItemUpdateComponent>;
    let service: TecnicasEvaluativasXItemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [TecnicasEvaluativasXItemUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TecnicasEvaluativasXItemUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TecnicasEvaluativasXItemUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TecnicasEvaluativasXItemService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TecnicasEvaluativasXItem(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TecnicasEvaluativasXItem();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
