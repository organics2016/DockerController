// **********************************************************************
//
// Copyright (c) 2003-2013 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.5.0
//
// <auto-generated>
//
// Generated from file `hello.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package p20160318.ice;

public final class PrinterHolder extends Ice.ObjectHolderBase<Printer>
{
    public
    PrinterHolder()
    {
    }

    public
    PrinterHolder(Printer value)
    {
        this.value = value;
    }

    public void
    patch(Ice.Object v)
    {
        if(v == null || v instanceof Printer)
        {
            value = (Printer)v;
        }
        else
        {
            IceInternal.Ex.throwUOE(type(), v);
        }
    }

    public String
    type()
    {
        return _PrinterDisp.ice_staticId();
    }
}
