export const PERIOD_TYPES = ['DIAS', 'MESES', 'ANOS', 'SEMANAS'] as const
type PeriodType = (typeof PERIOD_TYPES)[number]

export type MetadataUploadConfig = {
	fileExtension?: 'csv' | string
	hasHeader?: boolean | number
	fileName?: string
	separator?: string
}

export type MetadataConfig = {
	name?: string
	fileOrigin?: string
	frequencyNumber?: number | string
	frequencyType?: PeriodType | string
} & MetadataUploadConfig

export const VALID_COLUMN_TYPES = ['TEXTO', 'INTEIRO', 'DECIMAL', 'VERDADEIRO / FALSO', 'DATA'] as const
export type ValidColumnType = (typeof VALID_COLUMN_TYPES)[number]

export type ColumnConfig = {
	columnId?: Number
	columnName?: string
	columnNumber?:number
	type?: ValidColumnType | string
	canBeNull?: boolean | number
	description?: string
	status?: number
	hash?: number
	valid?: number
}

export type LZConfig = {
	fileId: number
	fileName: string
	fileOrigin: string
	frequencyType: string
	fileExtension: string
	frequencyNumber: number
	name: string
	columns: Array<{
		columnId: Number
		columnName: string
		description: string
		canBeNull: number
		status: number
		type: string
		hash: number
		valid: number
	}>
}
export type LZConfigView = {
	fileId?: number,
    name?: string,
    fileExtension?: string,
    fileOrigin?: string,
    frequencyNumber?: number,
    hasHeader?: number,
    frequencyType?: string,
    fileName?: string,
	columns?: ColumnConfig[]
}

export type BConfig = {
	fileId: number
	fileName: string
	fileOrigin: string
	filePeriod: string
	fileType: string
	frequency: number
	name: string
	columns: Array<{
		columnId: Number
		columnName: string
		description: string
		empty: number
		status: number
		type: string
		hash: number
		valid: number
	}>
}
