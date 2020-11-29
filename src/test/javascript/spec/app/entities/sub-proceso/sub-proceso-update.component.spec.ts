import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { SubProcesoUpdateComponent } from 'app/entities/sub-proceso/sub-proceso-update.component';
import { SubProcesoService } from 'app/entities/sub-proceso/sub-proceso.service';
import { SubProceso } from 'app/shared/model/sub-proceso.model';

describe('Component Tests', () => {
  describe('SubProceso Management Update Component', () => {
    let comp: SubProcesoUpdateComponent;
    let fixture: ComponentFixture<SubProcesoUpdateComponent>;
    let service: SubProcesoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [SubProcesoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SubProcesoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SubProcesoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SubProcesoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SubProceso(123);
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
        const entity = new SubProceso();
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
