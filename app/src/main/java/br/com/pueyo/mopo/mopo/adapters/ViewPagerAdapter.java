package br.com.pueyo.mopo.mopo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.pueyo.mopo.mopo.fragmentos.Fragmento;


/**
 * Created by 07669751770 on 14/06/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragmento> fragments = new ArrayList<>();


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position).getFragmento();
    }

    @Override
    public int getCount() {

        return fragments.size();
    }

    public void addFragment(Fragment fragment, String titulo){

        fragments.add(new Fragmento(fragment, titulo));
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return fragments.get(position).getTitulo();
    }
}