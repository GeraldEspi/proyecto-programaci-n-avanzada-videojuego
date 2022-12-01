package strategy;

public class StrategyFalcon implements StrategyObjMovi{
	
	@Override
	public int curarVidas(int vidas){
		vidas++;
		return vidas;
	}
	
	@Override
	public int curarVelx(){
		return 400;
	}
	
	@Override
	public int dañarVidas(int vidas){
		vidas--;
		return vidas;
	}
	
	@Override
	public int dañarVelx(int velx){
		return velx/2;
	}
	
	@Override
	public int dañarVidas(int vidas, int a){
		vidas -=3;
		return vidas;
	}
	
	@Override
	public int getVidas(){
		return 5;
	}
}
