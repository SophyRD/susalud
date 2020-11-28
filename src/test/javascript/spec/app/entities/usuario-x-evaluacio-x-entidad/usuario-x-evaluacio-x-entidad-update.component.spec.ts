import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { UsuarioXEvaluacioXEntidadUpdateComponent } from 'app/entities/usuario-x-evaluacio-x-entidad/usuario-x-evaluacio-x-entidad-update.component';
import { UsuarioXEvaluacioXEntidadService } from 'app/entities/usuario-x-evaluacio-x-entidad/usuario-x-evaluacio-x-entidad.service';
import { UsuarioXEvaluacioXEntidad } from 'app/shared/model/usuario-x-evaluacio-x-entidad.model';

describe('Component Tests', () => {
  describe('UsuarioXEvaluacioXEntidad Management Update Component', () => {
    let comp: UsuarioXEvaluacioXEntidadUpdateComponent;
    let fixture: ComponentFixture<UsuarioXEvaluacioXEntidadUpdateComponent>;
    let service: UsuarioXEvaluacioXEntidadService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [UsuarioXEvaluacioXEntidadUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(UsuarioXEvaluacioXEntidadUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UsuarioXEvaluacioXEntidadUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UsuarioXEvaluacioXEntidadService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UsuarioXEvaluacioXEntidad(123);
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
        const entity = new UsuarioXEvaluacioXEntidad();
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
