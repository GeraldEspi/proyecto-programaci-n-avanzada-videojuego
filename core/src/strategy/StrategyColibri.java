package strategy;

public class StrategyColibri implements StrategyObjMovi{
	
	@Override
	public int curarVidas(int vidas){
		vidas++;
		return vidas;
	}
	
	@Override
	public int curarVelx(){
		return 600;
	}
	
	@Override
	public int dañarVidas(int vidas){
		vidas-=2;
		return vidas;
	}
	
	@Override
	public int dañarVelx(int velx){
		velx-=100;
		return velx;
	}
	
	@Override
	public int dañarVidas(int vidas, int a){
		vidas-=5;
		return vidas;
	}
	
	@Override
	public int getVidas(){
		return 3;
	}
}

