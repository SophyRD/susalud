import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { MaestraverificadorUpdateComponent } from 'app/entities/maestraverificador/maestraverificador-update.component';
import { MaestraverificadorService } from 'app/entities/maestraverificador/maestraverificador.service';
import { Maestraverificador } from 'app/shared/model/maestraverificador.model';

describe('Component Tests', () => {
  describe('Maestraverificador Management Update Component', () => {
    let comp: MaestraverificadorUpdateComponent;
    let fixture: ComponentFixture<MaestraverificadorUpdateComponent>;
    let service: MaestraverificadorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [MaestraverificadorUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MaestraverificadorUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MaestraverificadorUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MaestraverificadorService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Maestraverificador(123);
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
        const entity = new Maestraverificador();
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
