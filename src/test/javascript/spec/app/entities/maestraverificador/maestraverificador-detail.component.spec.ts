import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { MaestraverificadorDetailComponent } from 'app/entities/maestraverificador/maestraverificador-detail.component';
import { Maestraverificador } from 'app/shared/model/maestraverificador.model';

describe('Component Tests', () => {
  describe('Maestraverificador Management Detail Component', () => {
    let comp: MaestraverificadorDetailComponent;
    let fixture: ComponentFixture<MaestraverificadorDetailComponent>;
    const route = ({ data: of({ maestraverificador: new Maestraverificador(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [MaestraverificadorDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MaestraverificadorDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MaestraverificadorDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load maestraverificador on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.maestraverificador).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
