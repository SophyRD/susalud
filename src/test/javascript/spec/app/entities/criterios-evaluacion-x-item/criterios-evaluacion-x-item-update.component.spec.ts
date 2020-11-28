import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { CriteriosEvaluacionXItemUpdateComponent } from 'app/entities/criterios-evaluacion-x-item/criterios-evaluacion-x-item-update.component';
import { CriteriosEvaluacionXItemService } from 'app/entities/criterios-evaluacion-x-item/criterios-evaluacion-x-item.service';
import { CriteriosEvaluacionXItem } from 'app/shared/model/criterios-evaluacion-x-item.model';

describe('Component Tests', () => {
  describe('CriteriosEvaluacionXItem Management Update Component', () => {
    let comp: CriteriosEvaluacionXItemUpdateComponent;
    let fixture: ComponentFixture<CriteriosEvaluacionXItemUpdateComponent>;
    let service: CriteriosEvaluacionXItemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [CriteriosEvaluacionXItemUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CriteriosEvaluacionXItemUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CriteriosEvaluacionXItemUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CriteriosEvaluacionXItemService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CriteriosEvaluacionXItem(123);
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
        const entity = new CriteriosEvaluacionXItem();
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
