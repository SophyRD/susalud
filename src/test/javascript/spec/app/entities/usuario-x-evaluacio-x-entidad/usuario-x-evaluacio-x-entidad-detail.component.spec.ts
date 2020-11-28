import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { UsuarioXEvaluacioXEntidadDetailComponent } from 'app/entities/usuario-x-evaluacio-x-entidad/usuario-x-evaluacio-x-entidad-detail.component';
import { UsuarioXEvaluacioXEntidad } from 'app/shared/model/usuario-x-evaluacio-x-entidad.model';

describe('Component Tests', () => {
  describe('UsuarioXEvaluacioXEntidad Management Detail Component', () => {
    let comp: UsuarioXEvaluacioXEntidadDetailComponent;
    let fixture: ComponentFixture<UsuarioXEvaluacioXEntidadDetailComponent>;
    const route = ({ data: of({ usuarioXEvaluacioXEntidad: new UsuarioXEvaluacioXEntidad(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [UsuarioXEvaluacioXEntidadDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UsuarioXEvaluacioXEntidadDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UsuarioXEvaluacioXEntidadDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load usuarioXEvaluacioXEntidad on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.usuarioXEvaluacioXEntidad).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
