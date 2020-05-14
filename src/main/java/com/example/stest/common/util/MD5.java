package com.example.stest.common.util;

import java.io.File;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 获取字符串或者文件的MD5
 */
public class MD5 {

private static char[]	hex	= { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	/**
	 * 获取字符串的MD5值
	 */
	public final static String compile ( String s )
	{
		
		byte[] tmp = s.getBytes ( );
		try
		{
			MessageDigest dig = MessageDigest.getInstance ( "MD5" );
			dig.update ( tmp );
			byte[] md = dig.digest ( );
			int j = md.length;
			char[] str = new char[j * 2];
			int k = 0;
			for ( int i = 0 ; i < j ; i++ )
			{
				byte byte0 = md[i];
				str[k++ ] = hex[byte0 >>> 4 & 0xf];
				str[k++ ] = hex[byte0 & 0xf];
			}
			return new String ( str );
		} catch ( NoSuchAlgorithmException ex )
		{
			return null;
		}
	}
	
	public static String MD5Encode ( byte[] toencode )
	{
		try
		{
			MessageDigest md5 = MessageDigest.getInstance ( "MD5" );
			md5.reset ( );
			md5.update ( toencode );
			return HexEncode ( md5.digest ( ) );
		} catch ( NoSuchAlgorithmException e ) 
		{ 
			e.printStackTrace();
		}
		return "";
	}
	
	public static byte[] MD5EncodeByte ( byte[] toencode )
	{
		try
		{
			MessageDigest md5 = MessageDigest.getInstance ( "MD5" );
			md5.reset ( );
			md5.update ( toencode );
			return md5.digest ( );
		} catch ( NoSuchAlgorithmException e ) 
		{ 
			e.printStackTrace();
		}
		return null;
	}
	
	public static String HexEncode ( byte[] toencode )
	{
		StringBuilder sb = new StringBuilder ( toencode.length * 2 );
		for ( byte b : toencode )
		{
			sb.append ( Integer.toHexString ( ( b & 0xf0 ) >>> 4 ) );
			sb.append ( Integer.toHexString ( b & 0x0f ) );
		}
		return sb.toString ( ).toUpperCase ( );
	}
	
	/**
	 * 获取文件的MD5值
	 */
	public static String compile ( File file ) throws NoSuchAlgorithmException , IOException
	{
		if ( !file.exists ( ) )
		{
			throw new FileNotFoundException ( );
		}
		
		InputStream fis;
		fis = new FileInputStream ( file );
		byte[] buffer = new byte[1024];
		MessageDigest md5 = MessageDigest.getInstance ( "MD5" );
		int numRead = 0;
		while ( ( numRead = fis.read ( buffer ) ) > 0 )
		{
			md5.update ( buffer , 0 , numRead );
		}
		fis.close ( );
		byte[] md = md5.digest ( );
		int j = md.length;
		char[] str = new char[j * 2];
		int k = 0;
		for ( int i = 0 ; i < j ; i++ )
		{
			byte byte0 = md[i];
			str[k++ ] = hex[byte0 >>> 4 & 0xf];
			str[k++ ] = hex[byte0 & 0xf];
		}
		return new String ( str );
	}
}
