import { IMacroproceso } from 'app/shared/model/macroproceso.model';
import { IProceso } from 'app/shared/model/proceso.model';
import { ISubproceso } from 'app/shared/model/subproceso.model';

export interface IVerificador {
  id?: number;
  codigo?: number;
  descripcion?: string;
  estado?: string;
  macroproceso?: IMacroproceso;
  proceso?: IProceso;
  subproceso?: ISubproceso;
}

export class Verificador implements IVerificador {
  constructor(
    public id?: number,
    public codigo?: number,
    public descripcion?: string,
    public estado?: string,
    public macroproceso?: IMacroproceso,
    public proceso?: IProceso,
    public subproceso?: ISubproceso
  ) {}
}
