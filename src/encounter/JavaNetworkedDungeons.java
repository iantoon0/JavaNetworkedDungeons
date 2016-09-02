package encounter;

import java.util.*;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import encounter.Classes.*;

public class JavaNetworkedDungeons {
	public static void main(String[] args){
		// TODO Auto-generated method stub
		Campaign c = new Campaign();
		JavaNetworkedDungeonsListener jndl = new JavaNetworkedDungeonsListener(c);
		jndl.run();
	}
}