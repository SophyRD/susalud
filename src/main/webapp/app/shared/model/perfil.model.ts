export interface IPerfil {
  id?: number;
  idPerfil?: number;
  peDescripcion?: string;
}

export class Perfil implements IPerfil {
  constructor(public id?: number, public idPerfil?: number, public peDescripcion?: string) {}
}
