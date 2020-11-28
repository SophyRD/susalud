import { IProceso } from 'app/shared/model/proceso.model';
import { IVerificador } from 'app/shared/model/verificador.model';

export interface IMacroproceso {
  id?: number;
  descripcion?: string;
  procesos?: IProceso[];
  verificadors?: IVerificador[];
}

export class Macroproceso implements IMacroproceso {
  constructor(public id?: number, public descripcion?: string, public procesos?: IProceso[], public verificadors?: IVerificador[]) {}
}
