import java.lang.Math; 

public class main{
	public float inclusiveRandom(){
	if(Math.random() == 0)
    	if(Math.random()>=0.5)
    		return 1f;
    	else
    		return 0f;
	else
    	return (float)Math.random();
	}
	public static void main(String []args){
		long[] d = new long[18];
		for (int i = 18; i > 0; i--){
			d[18 - i] = i;
		}
		float[] x = new float[13];
		for (int i = 0; i < x.length; i++){
			x[i] = -3.0f + new main().inclusiveRandom() * 16;
		}
		double[][] q = new double[18][13];
		for (int i = 0; i < 18; i++){
			for(int j = 0; j < 13; j++){
				double y = (x[j]+5)/16; //вспомогательная переменная, тк много где используется она
				switch ((int)d[i]){
					case 3:
						q[i][j] = Math.pow(Math.cos(Math.atan(y)), Math.pow(4/(2+Math.tan(x[j])), 3) / 4);
						break;
					case 1:
					case 2:
					case 5:
					case 7:
					case 11:
					case 12:
					case 13:
					case 17:
					case 18:
						q[i][j] = Math.pow(Math.E, Math.atan(Math.pow(y,2)));
						break;
					default:
						q[i][j] = Math.cbrt(Math.pow(Math.pow(1/(1./3-Math.asin(y))/2, 3),Math.cos(Math.tan(x[j]))));
						break;
				}
			}
		}
		for(int i = 0; i < 18; i++){
			System.out.printf("'%-6.4f'  '%-6.4f'  '%-6.4f'  '%-6.4f'  '%-6.4f'  '%-6.4f'  '%-6.4f'  '%-6.4f'  '%-6.4f'  '%-6.4f'  '%-6.4f'  '%-6.4f'  '%-6.4f'  ", q[i][0],q[i][1],q[i][2],q[i][3],q[i][4],q[i][5],q[i][6],q[i][7],q[i][8],q[i][9],q[i][10],q[i][11],q[i][12]);
			
			System.out.println();
		}
	}
}